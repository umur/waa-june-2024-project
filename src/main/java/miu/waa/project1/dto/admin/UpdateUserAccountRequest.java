package miu.waa.project1.dto.admin;

import lombok.Getter;
import miu.waa.project1.common.AccountStatus;

@Getter
public class UpdateUserAccountRequest {
	Long id;
	AccountStatus status;
}
