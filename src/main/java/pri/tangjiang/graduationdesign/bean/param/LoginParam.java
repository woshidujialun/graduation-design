package pri.tangjiang.graduationdesign.bean.param;

import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class LoginParam {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
