import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class Demo1 {
    public static long start = 0;
    // 注意，运行这个方法之前，请先运行mvn initialize把测试数据灌入数据库
    public static void main(String[] args) throws SQLException {
        start = System.currentTimeMillis();
        slowFileOpt();
        new Thread(Demo1::slowFileOpt).start();
        new Thread(Demo1::slowFileOpt).start();
        new Thread(Demo1::slowFileOpt).start();
        System.out.println("time spend="+(System.currentTimeMillis() - start));
    }

    private static void slowFileOpt(){
        try{
            File file = File.createTempFile("tmp", "");//前缀和后缀？
            for(int i=0; i<10000;i++){
                try (FileOutputStream fos = new FileOutputStream(file)){
                    fos.write(i);
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("time="+(end-start)+" ms");//6112
        }catch (IOException exp){
            throw new RuntimeException(exp);
        }
        System.out.println("end");
    }
}
