package wl.hdzj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import wl.hdzj.converter.ModelConverter;
import wl.hdzj.dao.MemberRepository;
import wl.hdzj.domain.MemberVO;
import wl.hdzj.entity.Member;
import wl.hdzj.service.MemberService;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService{
    //private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MemberRepository memberRepository;
    @Value("${my.uf.upload}")
    private String uppath;

    @Cacheable(value = "hdzj2016_cache")
    public List<Member> findShow(){
        return memberRepository.findShow();
    }

    @Override
    public Member add(MemberVO memberVO, String sid) throws Exception {
        //获取图片id
        String iid = memberVO.getPic();
        //判断图片是否存在
        if (iid != null && !iid.isEmpty() && stringRedisTemplate.opsForHash().size(iid) != 0) {
            Map<Object, Object> em = stringRedisTemplate.opsForHash().entries(iid);
            //校验Session ID
            if (!em.get("sessionid").toString().equals(sid)) throw new Exception("不属于当前用户");
            //临时缓存字符串
            String temp = em.get("mime").toString();
            //计算文件名
            String tname = iid + "." + temp.substring(temp.lastIndexOf("/") + 1, temp.length());
            File file = new File(em.get("path").toString(), tname);
            if (!file.exists()) throw new Exception("文件错误");
            //关联装载
            //存储记录
            Member result = memberRepository.save(
                    (Member) ModelConverter.convert(Member.class).apply(memberVO)
            );
            //移动文件到持久目录
            File dar = new File(String.format("%s%s", this.getClass().getClassLoader().getResource("").getPath(), uppath));
            if (!dar.exists() && !dar.mkdir()) throw new Exception("文件错误");
            if (!file.renameTo(new File(dar, file.getName()))) throw new Exception("文件错误");
            return result;
        } else {
            throw new Exception("图片参数错误");
        }
    }

    @Override
    public Page<Member> gets(Pageable p) {
        return memberRepository.findAll(p);
    }

    @Override
    public long deleteByIDs(List<Integer> ids) {
        long count = 0;
        for (Integer v : ids) {
            count += memberRepository.deleteByMid(v);
        }
        return count;
    }

    @Override
    public Member update(MemberVO memberVO) throws Exception{
        if (memberVO.getMid() == null) return null;
        Member gn = memberRepository.getOne(memberVO.getMid());
        if (gn == null || gn.getMid() == null) throw new Exception("修改项目不存在");
        ModelConverter.setUpdateEntity(gn, memberVO);
        return memberRepository.saveAndFlush(gn);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<Member> querys(MemberVO memberVO, Pageable p) {
        return memberRepository.findAll(ModelConverter.setQueryStatement(memberVO),p);
    }
}
