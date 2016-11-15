package wl.hdzj.web;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import wl.hdzj.common.AddVaild;
import wl.hdzj.common.UpdateVaild;
import wl.hdzj.converter.ModelConverter;
import wl.hdzj.domain.*;
import wl.hdzj.entity.*;
import wl.hdzj.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *  后台管理相关API
 *  @author lipengbiao
 */
@RestController
public class BackController {
    private static final String SUPPORT_IMAGE_LIST = "image/gif,image/png,image/jpeg";
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    NewsService newsService;
    @Autowired
    MemberService memberService;
    @Autowired
    ColumnService columnService;
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;
    @Autowired
    RelationService relationService;
    @Value("${my.uf.temp}")
    private String utempp;
    @Value("${my.uf.upload}")
    private String uulp;

    public static String getVailError(BindingResult vr) {
        /*
        StringBuilder sb = new StringBuilder('[');
        List<ObjectError> le = vr.getAllErrors();
        for (ObjectError oe: le) {
            sb.append(oe.getObjectName());
            sb.append(":");
            sb.append(oe.getDefaultMessage());
            sb.append(",");
        }
        sb.append(']');
        */
        List<ObjectError> le = vr.getAllErrors();
        return le.get(0).getObjectName() + ":" + le.get(0).getDefaultMessage();
    }

    /**
     * 分页参数检查器
     *
     * @param pv
     * @return
     */
    private static PageRequest pageFiler(PageVO pv) {
        if (pv.getPage() != null & pv.getSize() != null &&
                pv.getSortc() != null && pv.getSort() != null) {
            return new PageRequest(pv.getPage(), pv.getSize(),
                    (pv.getSort() == 0) ? Sort.Direction.ASC : Sort.Direction.DESC,
                    pv.getSortc());
        } else if (pv.getPage() != null & pv.getSize() != null) {
            return new PageRequest(pv.getPage(), pv.getSize());
        } else {
            return null;
        }
    }

    /** 添加关系
     * @param vo 领域模型
     * @return 返回结果
     */
    @RequestMapping(value = "/add/relation", method = RequestMethod.POST)
    public
    @ResponseBody
    MessageVO<RelationVO> add(@Validated({AddVaild.class}) RelationVO vo) {
        
        try {
            Relation data = relationService.add(vo);
            RelationVO nv = (RelationVO) ModelConverter.convert(RelationVO.class).apply(data);
            return new MessageVO<>(nv);
        } catch (Exception e) {
            return new MessageVO<>("添加失败", e.getMessage());
        }
    }

    /** 删除gx
     * @param id_list 删除的id列表
     */
    @RequestMapping(value = "/delete/relation", method = RequestMethod.DELETE)
    public @ResponseBody MessageVO<Long> deleteRelation(@RequestBody List<Integer> id_list){
        long c = relationService.deleteByIDs(id_list);
        return new MessageVO<>(c);
    }

    /** 更新gx
     * @param rv
     * @return
     */
    @RequestMapping(value = "/update/relation", method = RequestMethod.POST)
    public
    @ResponseBody
    MessageVO<RelationVO> update(@Validated({UpdateVaild.class}) RelationVO rv) {
        
        try {
            RelationVO result = (RelationVO) ModelConverter.convert(RelationVO.class).apply(relationService.update(rv)) ;
            return new MessageVO<>(result);
        } catch (Exception e) {
            return new MessageVO<>("更新失败", e.getMessage());
        }
    }

    /** gx多条件查询
     * @param rv 后台用户领域模型
     * @param pv 分页参数
     * @return 分页
     */
    @RequestMapping(value = "/query/relation", method = RequestMethod.GET)
    public MessageVO<Page> query(RelationVO rv, PageVO pv) {
        
        PageRequest pq = pageFiler(pv);
        return (pq == null) ? new MessageVO<>("获取失败", "参数错误") :
                new MessageVO<>(relationService.querys(rv, pq));
    }

    /**
     * 获取gx（支持分页和单列排序）
     * @param pv PageRequest 领域模型
     * @return 返回结果
     */
    @RequestMapping(value = "/get/relation", method = RequestMethod.GET)
    public
    @ResponseBody
    MessageVO<Page> getRelation(@Valid PageVO pv) {
        
        PageRequest pq = pageFiler(pv);
        return (pq == null) ? new MessageVO<>("获取失败", "参数错误") :
                new MessageVO<>(relationService.gets(pq));
    }

    /** 添加后台用户
     * @param vo 领域模型
     * @return 返回结果
     */
    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    public
    @ResponseBody
    MessageVO<UserVO> add(@Valid UserVO vo) {
        
        try {
            User data = userService.add(vo);
            UserVO nv = (UserVO) ModelConverter.convert(UserVO.class).apply(data);
            return new MessageVO<>(nv);
        } catch (Exception e) {
            return new MessageVO<>("添加失败", e.getMessage());
        }
    }

    /** 删除后台用户
     * @param id_list 删除的id列表
     */
    @RequestMapping(value = "/delete/user", method = RequestMethod.DELETE)
    public @ResponseBody MessageVO<Long> deleteUser(@RequestBody List<Integer> id_list){
        long c = userService.deleteByIDs(id_list);
        return new MessageVO<>(c);
    }

    /** 更新后台用户
     * @param uv
     * @return
     */
    @RequestMapping(value = "/update/user", method = RequestMethod.POST)
    public
    @ResponseBody
    MessageVO<UserVO> update(@Valid UserVO uv) {
        
        try {
            UserVO result = (UserVO) ModelConverter.convert(UserVO.class).apply(userService.update(uv)) ;
            return new MessageVO<>(result);
        } catch (Exception e) {
            return new MessageVO<>("更新失败", e.getMessage());
        }
    }

    /** 后台用户多条件查询
     * @param uv 后台用户领域模型
     * @param pv 分页参数
     * @return 分页
     */
    @RequestMapping(value = "/query/user", method = RequestMethod.GET)
    public MessageVO<Page> query(@Valid UserVO uv, PageVO pv) {
        
        PageRequest pq = pageFiler(pv);
        return (pq == null) ? new MessageVO<>("获取失败", "参数错误") :
                new MessageVO<>(userService.querys(uv, pq));
    }

    /**
     * 获取后台用户列表（支持分页和单列排序）
     * @param pv PageRequest 领域模型
     * @return 返回结果
     */
    @RequestMapping(value = "/get/user", method = RequestMethod.GET)
    public
    @ResponseBody
    MessageVO<Page> getUser(@Valid PageVO pv) {
        
        PageRequest pq = pageFiler(pv);
        return (pq == null) ? new MessageVO<>("获取失败", "参数错误") :
                new MessageVO<>(userService.gets(pq));
    }

    /** 添加团队
     * @param vo 领域模型
     * @return 返回结果
     */
    @RequestMapping(value = "/add/team", method = RequestMethod.POST)
    public
    @ResponseBody
    MessageVO<TeamVO> add(@Valid TeamVO vo) {
        
        try {
            Team data = teamService.add(vo);
            TeamVO nv = (TeamVO) ModelConverter.convert(TeamVO.class).apply(data);
            return new MessageVO<>(nv);
        } catch (Exception e) {
            return new MessageVO<>("添加失败", e.getMessage());
        }
    }

    /** 删除团队
     * @param id_list 删除的id列表
     */
    @RequestMapping(value = "/delete/team", method = RequestMethod.DELETE)
    public @ResponseBody MessageVO<Long> deleteTeam(@RequestBody List<Integer> id_list){
        long c = teamService.deleteByIDs(id_list);
        return new MessageVO<>(c);
    }

    /** 更新团队
     * @param tv
     * @return
     */
    @RequestMapping(value = "/update/team", method = RequestMethod.POST)
    public
    @ResponseBody
    MessageVO<TeamVO> update(@Valid TeamVO tv) {
        
        try {
            TeamVO result = (TeamVO) ModelConverter.convert(TeamVO.class).apply(teamService.update(tv)) ;
            return new MessageVO<>(result);
        } catch (Exception e) {
            return new MessageVO<>("更新失败", e.getMessage());
        }
    }

    /** 团队多条件查询
     * @param tv 成员领域模型
     * @param pv 分页参数
     * @return 分页
     */
    @RequestMapping(value = "/query/team", method = RequestMethod.GET)
    public MessageVO<Page> query(@Valid TeamVO tv, @Valid PageVO pv) {
        
        PageRequest pq = pageFiler(pv);
        return (pq == null) ? new MessageVO<>("获取失败", "参数错误") :
                new MessageVO<>(teamService.querys(tv, pq));
    }

    /**
     * 获取团队列表（支持分页和单列排序）
     * @param pv PageRequest 领域模型
     * @return 返回结果
     */
    @RequestMapping(value = "/get/team", method = RequestMethod.GET)
    public
    @ResponseBody
    MessageVO<Page> getTeam(@Valid PageVO pv) {
        
        PageRequest pq = pageFiler(pv);
        return (pq == null) ? new MessageVO<>("获取失败", "参数错误") :
                new MessageVO<>(teamService.gets(pq));
    }

    /** 添加栏目
     * @param vo 领域模型
     * @return 返回结果
     */
    @RequestMapping(value = "/add/column", method = RequestMethod.POST)
    public
    @ResponseBody
    MessageVO<ColumnVO> add(@Valid ColumnVO vo) {
        
        try {
            Columnnn data = columnService.add(vo);
            ColumnVO nv = (ColumnVO) ModelConverter.convert(ColumnVO.class).apply(data);
            return new MessageVO<>(nv);
        } catch (Exception e) {
            return new MessageVO<>("添加失败", e.getMessage());
        }
    }

    /** 删除栏目
     * @param id_list 删除的id列表
     */
    @RequestMapping(value = "/delete/column", method = RequestMethod.DELETE)
    public @ResponseBody MessageVO<Long> deleteColumn(@RequestBody List<Integer> id_list){
        long c = columnService.deleteByIDs(id_list);
        return new MessageVO<>(c);
    }

    /** 更新栏目
     * @param cv
     * @return
     */
    @RequestMapping(value = "/update/column", method = RequestMethod.POST)
    public
    @ResponseBody
    MessageVO<ColumnVO> update(@Valid ColumnVO cv) {
        
        try {
            ColumnVO result = (ColumnVO) ModelConverter.convert(ColumnVO.class).apply(columnService.update(cv)) ;
            return new MessageVO<>(result);
        } catch (Exception e) {
            return new MessageVO<>("更新失败", e.getMessage());
        }
    }

    /** 栏目多条件查询
     * @param cv 成员领域模型
     * @param pv 分页参数
     * @return 分页
     */
    @RequestMapping(value = "/query/column", method = RequestMethod.GET)
    public MessageVO<Page> query(@Valid ColumnVO cv, @Valid PageVO pv) {
        
        PageRequest pq = pageFiler(pv);
        return (pq == null) ? new MessageVO<>("获取失败", "参数错误") :
                new MessageVO<>(columnService.querys(cv, pq));
    }

    /**
     * 获取栏目列表（支持分页和单列排序）
     * @param pv PageRequest 领域模型
     * @return 返回结果
     */
    @RequestMapping(value = "/get/column", method = RequestMethod.GET)
    public
    @ResponseBody
    MessageVO<Page> getColumn(@Valid PageVO pv) {
        
        PageRequest pq = pageFiler(pv);
        return (pq == null) ? new MessageVO<>("获取失败", "参数错误") :
                new MessageVO<>(columnService.gets(pq));
    }

    /** 添加成员
     * @param memberVO 领域模型
     * @return 返回结果
     */
    @RequestMapping(value = "/add/member", method = RequestMethod.POST)
    public
    @ResponseBody
    MessageVO<MemberVO> add(@Valid MemberVO memberVO, HttpServletRequest request) {
        
        try {
            Member data = memberService.add(memberVO, request.getSession().getId());
            MemberVO nv = (MemberVO) ModelConverter.convert(MemberVO.class).apply(data);
            return new MessageVO<>(nv);
        } catch (Exception e) {
            return new MessageVO<>("添加失败", e.getMessage());
        }
    }

    /** 根据 nid 删除成员
     * @param id_list 删除的id列表
     */
    @RequestMapping(value = "/delete/member", method = RequestMethod.DELETE)
    public @ResponseBody MessageVO<Long> deleteMember(@RequestBody List<Integer> id_list){
        long c = memberService.deleteByIDs(id_list);
        return new MessageVO<>(c);
    }

    /** 更新成员
     * @param mv
     * @return
     */
    @RequestMapping(value = "/update/member", method = RequestMethod.POST)
    public
    @ResponseBody
    MessageVO<MemberVO> update(@Valid MemberVO mv) {
        
        try {
            MemberVO result = (MemberVO) ModelConverter.convert(MemberVO.class).apply(memberService.update(mv)) ;
            return new MessageVO<>(result);
        } catch (Exception e) {
            return new MessageVO<>("更新失败", e.getMessage());
        }
    }

    /** 成员多条件查询
     * @param mv 成员领域模型
     * @param pv 分页参数
     * @return 分页
     */
    @RequestMapping(value = "/query/member", method = RequestMethod.GET)
    public MessageVO<Page> query(@Valid MemberVO mv, @Valid PageVO pv) {
        
        PageRequest pq = pageFiler(pv);
        return (pq == null) ? new MessageVO<>("获取失败", "参数错误") :
                new MessageVO<>(memberService.querys(mv, pq));
    }

    /**
     * 获取成员列表（支持分页和单列排序）
     * @param pv PageRequest 领域模型
     * @return 返回结果
     */
    @RequestMapping(value = "/get/member", method = RequestMethod.GET)
    public
    @ResponseBody
    MessageVO<Page> getMembers(@Valid PageVO pv) {
        
        PageRequest pq = pageFiler(pv);
        return (pq == null) ? new MessageVO<>("获取失败", "参数错误") :
                new MessageVO<>(memberService.gets(pq));
    }

    /** 添加新闻
     * @param newsVO 新闻的领域模型
     * @param request HttpServletRequest
     * @return 返回结果
     */
    @RequestMapping(value = "/add/news", method = RequestMethod.POST)
    public
    @ResponseBody
    MessageVO<NewsVO> add(@Valid NewsVO newsVO, HttpServletRequest request) {
        
        try {
            News data = newsService.add(newsVO, request.getSession().getId());
            NewsVO nv = (NewsVO) ModelConverter.convert(NewsVO.class).apply(data);
            return new MessageVO<>(nv);
        } catch (Exception e) {
            return new MessageVO<>("添加失败", e.getMessage());
        }
    }

    /** 根据 nid 删除新闻
     * @param id_list 删除的id列表
     */
    @RequestMapping(value = "/delete/news", method = RequestMethod.DELETE)
    public @ResponseBody MessageVO<Long> deletenews(@RequestBody List<Integer> id_list){
        long c = newsService.deleteByIDs(id_list);
        return new MessageVO<>(c);
    }

    /** 更新新闻
     * @param nv
     * @return
     */
    @RequestMapping(value = "/update/news", method = RequestMethod.POST)
    public
    @ResponseBody
    MessageVO<NewsVO> update(@Validated({UpdateVaild.class}) NewsVO nv) {
        
        try {
            NewsVO result = (NewsVO) ModelConverter.convert(NewsVO.class).apply(newsService.update(nv)) ;
            return new MessageVO<>(result);
        } catch (Exception e) {
            return new MessageVO<>("更新失败", e.getMessage());
        }
    }

    /** 新闻多条件查询
     * @param nv 新闻领域模型
     * @param pv 分页参数
     * @return 分页
     */
    @RequestMapping(value = "/query/news", method = RequestMethod.GET)
    public MessageVO<Page> query(@Valid NewsVO nv, @Valid PageVO pv) {
        
        PageRequest pq = pageFiler(pv);
        return (pq == null) ? new MessageVO<>("获取失败", "参数错误") :
                new MessageVO<>(newsService.querys(nv, pq));
    }

    /**
     * 获取新闻列表（支持分页和单列排序）
     * @param pv PageRequest 领域模型
     * @return 返回结果
     */
    @RequestMapping(value = "/get/news", method = RequestMethod.GET)
    public
    @ResponseBody
    MessageVO<Page> getNews(@Valid PageVO pv) {
        
        PageRequest pq = pageFiler(pv);
        return (pq == null) ? new MessageVO<>("获取失败", "参数错误") :
                new MessageVO<>(newsService.gets(pq));
    }

    /** 多图片上传接口
     * @param request 请求
     * @return 处理结果
     */
    //@Async //异步操作
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody MessageVO uploadimg(HttpServletRequest request){
        List<MultipartFile> imgs =((MultipartHttpServletRequest)request).getFiles("img");
        //KEY构造器
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        try {
            //返回浏览器的结果列表
            List<String> result = new ArrayList<>();
            for (MultipartFile imgs_v: imgs) {
                if (!imgs_v.isEmpty()){
                    //进行文件类型检查
                    if(!SUPPORT_IMAGE_LIST.contains(imgs_v.getContentType().trim().toLowerCase())) break;
                    //生成image key
                    int random = (int) (Math.random() * 10000);
                    String imgkey = md5.encodePassword(imgs_v.getOriginalFilename() + Long.toString(System.currentTimeMillis()), random);
                    //获得临时存取路径
                    String path = FileUtils.getTempDirectoryPath() + utempp;
                    //生成文件名
                    String newFileName = imgkey + "." + imgs_v.getContentType().substring(imgs_v.getContentType().lastIndexOf("/") + 1, imgs_v.getContentType().length());
                    //存储
                    File thefile = new File(path, newFileName);
                    FileUtils.writeByteArrayToFile(thefile, imgs_v.getBytes());
                    //生成缩略图
                    File thufile = new File(path, "thu_" + newFileName);
                    Thumbnails.of(thefile).size(200, 200).keepAspectRatio(true).toFile(thufile);
                    //缓存文件映射信息
                    stringRedisTemplate.opsForHash().put(imgkey, "sessionid", request.getSession().getId());
                    stringRedisTemplate.opsForHash().put(imgkey, "path", path);
                    stringRedisTemplate.opsForHash().put(imgkey, "mime", imgs_v.getContentType());
                    //设置过期时间
                    stringRedisTemplate.expire(imgkey, 5, TimeUnit.MINUTES);
                    // 添加imgkey到返回结果
                    result.add(imgkey);
                }
            }
            return new MessageVO<>(result);
        } catch (Exception e){
            return new MessageVO<>("上传错误", e.getMessage());
        }
    }

    /** 获取上传图片缓存缩略图API
     * @param iid imgkey
     * @param request 请求
     * @param response 响应
     */
    //@Async //异步操作
    @RequestMapping(value = "/get/img")
    public void getimg(@Param(value = "iid") String iid, HttpServletRequest request, HttpServletResponse response){
        //判断缓存是否存在
        if (stringRedisTemplate.opsForHash().size(iid) != 0){
            try {
                //取缓存
                Map<Object, Object> em = stringRedisTemplate.opsForHash().entries(iid);
                //校验Session ID
                if (!em.get("sessionid").toString().equals(request.getSession().getId())) return;
                //临时缓存字符串
                String temp = em.get("mime").toString();
                //计算文件名
                String tname = "thu_" + iid + "." + temp.substring(temp.lastIndexOf("/") + 1, temp.length());
                //获得缩略图文件
                File file = new File(em.get("path").toString() , tname);
                //判断文件是否存在
                if (file.exists()){
                    //设置返回结果MIME类型
                    response.setContentType(temp);
                    //创建文件输入流
                    FileInputStream inputStream = new FileInputStream(file);
                    //获得输出流
                    OutputStream outputStream = response.getOutputStream();
                    //创建缓冲区
                    byte[] b = new byte[1024];
                    //输入转输出
                    while( inputStream.read(b)!= -1){
                        //写入
                        outputStream.write(b);
                    }
                    //刷新
                    outputStream.flush();
                    //关闭
                    outputStream.close();
                    inputStream.close();
                }
            } catch (Exception e){
                response.setStatus(500);
            }
        }
    }
}
