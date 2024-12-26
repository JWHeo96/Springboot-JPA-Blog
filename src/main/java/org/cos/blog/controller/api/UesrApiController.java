package org.cos.blog.controller.api;

import org.cos.blog.dto.ResponseDto;
import org.cos.blog.model.RoleType;
import org.cos.blog.model.User;
import org.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class UesrApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController : save 호출됨");
        // 실제로 DB에 insert를 하고 아래에서 return이 되면 된다
        user.setRole(RoleType.USER);
        int result = userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK, result); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
    }
}
