import com.example.mysterybook.utils.DatabaseUtil;



public class Main {
    public static void main(String[] args) {
        try {
            DatabaseUtil.getInstance().getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
