package test;

import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;

public class MyCssExtension implements ConcordionExtension {
	    public void addTo(ConcordionExtender concordionExtender) {
	        concordionExtender.withLinkedCSS("/concordion.css",
	            new Resource("/concordion.css"));
	    }
	}