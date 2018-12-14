package sergei.webservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CryptoCurrency {

		    @Id
		    @GeneratedValue
		    public Long id;

		    public String name;
		    public String description;
		    public Long price;
			public String getName() {return name;
				
				
			}
			public Long getPrice() {return this.price;
			
			
			}
			public void setPrice(Long price2) {
			this.price = price2;
			
			}
			public String getDescription()
			{
				return this.description;
			}
			public void setDescription(String n)
			{
				this.description = n;
			}
			public void setName(String name2) {
				
				this.name = name2;
			}
			public void setId(Long id2) {
				this.id = id2;
				
			}
	
}
