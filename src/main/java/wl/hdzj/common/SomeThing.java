package wl.hdzj.common;

import java.io.File;
import java.util.Map;
import java.util.function.Supplier;


public class SomeThing {

    public static Object transferImg(String uploadpath, String imk, Supplier f, Map<Object, Object> em, String tname) throws Exception {
        File file = new File(em.get("path").toString(), tname);
        if (!file.exists()) throw new Exception("文件不存在");
        Object result = f.get();
        //移动文件到持久目录
        File dar = new File(uploadpath);
        if (!dar.exists() && !dar.mkdir()) throw new Exception("目录创建失败");
        if (!file.renameTo(new File(dar, file.getName()))) throw new Exception("文件转移失败");
        return result;
    }
}
