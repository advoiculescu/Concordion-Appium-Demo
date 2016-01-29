package test;

import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;

public class MyCssExtension implements ConcordionExtension {
	// Extensi�n que incluye el archivo CSS situado en la carpeta
	// src/test/resources dentro del target (la carpeta de generaci�n del HTML
	// final) y la asocia a �l, a�adiendo un estilo personalizado al archivo HTML
	public void addTo(ConcordionExtender concordionExtender) {
		concordionExtender.withLinkedCSS("/concordion.css", new Resource("/test/concordion.css"));
	}
}