package fun.moyujian.hause.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HouseStatus {

    UNDER_REVIEW(0, "审核中"),
    PUBLISHED(1, "已发布"),
    REVIEW_FAILED(2, "审核未通过")
    ;

    private final int code;
    private final String status;

    public static HouseStatus getByCode(int code) {
        for (HouseStatus value : HouseStatus.values()) {
            if (code == value.code) {
                return value;
            }
        }
        return null;
    }
}
