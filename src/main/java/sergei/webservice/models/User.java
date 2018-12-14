package sergei.webservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	    @Id
	    @GeneratedValue
	    public Long id;

	    public String name;
	    public String password;
}
