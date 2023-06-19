import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class text {
    @Test
public void text01(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.matches("123456", "$2a$10$pK6CFWA.UWlDQyX4oR4gx.nVmafK9OjevKIu2VX0cDaJUQ8gpYhz."));
    }
}
