package fileReader;

import cache.Cache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileReaderTaskTest {

    @Mock
    FileReader fileReader;

    @Mock
    Cache cache;

    private final File file = new File("some/file/path");

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void readsFileWhenTaskIsExecuted() throws Exception {
        FileReaderTask task = new FileReaderTask(fileReader, file, cache);

        task.run();

        verify(fileReader).extractWordsFrom(file);
    }

    @Test
    public void cachesFileContent() throws Exception {
        FileReaderTask task = new FileReaderTask(fileReader, file, cache);

        task.run();

        verify(cache).cache(anyString(), any(List.class));
    }
}