package com.penroz.kafka.spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static com.penroz.kafka.spring.Application.PValue;
import static com.penroz.kafka.spring.Application.PValueBuilder;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    @Mock
    private KafkaTemplate<String, PValue> mockKafkaTmplt;
    @Mock
    private PValueBuilder mockValueBldr;
    @InjectMocks
    private Application app = new Application();

    @Mock
    private PValue mockVal;

    @Before
    public void beforeTest() {
        ReflectionTestUtils.setField(app, "numOfTestMsgs", 1);
    }

    @Test
    public void testRun() throws Exception {
        doReturn(mockVal).when(mockValueBldr).create();
        doReturn("666").when(mockVal).getId();

        app.run(null);

        verify(mockKafkaTmplt).sendDefault(anyString(), eq(mockVal));
    }

    @Test
    public void testListen() {
        when(mockVal.toString()).thenReturn("Testing Receiving messages...:-D");

        app.listen(mockVal);
    }
}
