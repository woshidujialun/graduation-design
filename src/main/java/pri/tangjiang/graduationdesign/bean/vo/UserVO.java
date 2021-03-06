package pri.tangjiang.graduationdesign.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Long UserId;

    private String username;

    private Integer departmentId;

    private String birthday;

    private String gender;

    private String nickname;

    private Byte admin;

    private String departmentName;

    private String description;
}
