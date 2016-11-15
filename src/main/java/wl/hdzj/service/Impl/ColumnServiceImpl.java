package wl.hdzj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import wl.hdzj.converter.ModelConverter;
import wl.hdzj.dao.ColumnRepository;
import wl.hdzj.domain.ColumnVO;
import wl.hdzj.entity.Columnnn;
import wl.hdzj.service.ColumnService;

import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService {
    @Autowired
    ColumnRepository columnRepository;

    @Override
    public Columnnn add(ColumnVO colmnVO) throws Exception {
        //存储记录
        return columnRepository.save(
                (Columnnn) ModelConverter.convert(Columnnn.class).apply(colmnVO)
        );
    }

    @Override
    public Page<Columnnn> gets(Pageable p) {
        return columnRepository.findAll(p);
    }

    @Override
    public long deleteByIDs(List<Integer> ids) {
        long count = 0;
        for (Integer v : ids) {
            count += columnRepository.deleteByCid(v);
        }
        return count;
    }

    @Override
    public Columnnn update(ColumnVO colmnVO) throws Exception {
        if (colmnVO.getCid() == null) return null;
        Columnnn gn = columnRepository.getOne(colmnVO.getCid());
        //无关联项
        if (gn == null || gn.getCid() == null) throw new Exception("修改项目不存在");
        ModelConverter.setUpdateEntity(gn, colmnVO);
        return columnRepository.saveAndFlush(gn);
    }

    @Override
    public Page<Columnnn> querys(ColumnVO colmnVO, Pageable p) {
        return columnRepository.findAll(ModelConverter.setQueryStatement(colmnVO),p);
    }
}
