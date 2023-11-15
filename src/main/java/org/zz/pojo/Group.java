package org.zz.pojo;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author songjiaqiang  2023/11/14
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Group {
    private long groupId;
    private long sponsor;
    private List<Long> receiveIds;
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
