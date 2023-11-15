package org.zz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author songjiaqiang  2023/11/14
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    /**
     * 用户id
     */
    long id;

    String userName;

    String passWord;

    /**
     * 在线状态
     */
    String state;

    public static void main(String[] args) {

    }
}
