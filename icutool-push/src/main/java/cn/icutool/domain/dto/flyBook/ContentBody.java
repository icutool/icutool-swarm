package cn.icutool.domain.dto.flyBook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentBody {
    private Map<String, ViewContent> post;
}