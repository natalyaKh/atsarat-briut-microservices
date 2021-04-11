package smilyk.atsarat.children.service.children;

import org.springframework.stereotype.Service;
import smilyk.atsarat.children.dto.AddChildDto;
import smilyk.atsarat.children.dto.Response;

import java.util.List;

@Service
public interface ChildService {
    Response addChild(AddChildDto childDetails);

}
