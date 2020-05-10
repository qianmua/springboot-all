package mapper;

import entity.LearnResource;
import java.util.List;

public interface LearnResourceMapper {
    int insert(LearnResource record);

    List<LearnResource> selectAll();
}