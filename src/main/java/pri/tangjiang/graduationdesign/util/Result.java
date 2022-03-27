package pri.tangjiang.graduationdesign.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String codeMsg;
    private T data;

    public static <T> Result ok(T data) {
        return new Result<T>(200, "处理成功", data);
    }

    public static <T> Result ok() {
        return new Result<T>(200, "处理成功", null);
    }

    public static <T> Result fail(String message) {
        return Result.builder().code(500).codeMsg(message).build();
    }

    public static <T> Result fail() {
        return Result.builder().code(500).codeMsg("fail").build();
    }
}
