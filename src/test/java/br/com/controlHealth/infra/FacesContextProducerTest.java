package br.com.controlHealth.infra;

import javax.faces.context.FacesContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class FacesContextProducerTest {

	@Mock
	private FacesContextProducer contextProducer;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldGetFacesContext() {
		FacesContext facesContextMock = Mockito.mock(FacesContext.class);
		Mockito.when(contextProducer.getFacesContext()).thenReturn(facesContextMock);
		
		FacesContext context = contextProducer.getFacesContext();
		
		Assert.assertNotNull(context);
	}

}
