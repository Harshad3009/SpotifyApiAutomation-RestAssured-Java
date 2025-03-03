package pojos.generic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OuterError {

    private InnerError error;

}
