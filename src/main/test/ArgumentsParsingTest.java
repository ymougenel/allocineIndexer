import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ArgumentsParsingTest {


    @Test
    public void testDefaultValues() {
        assertEquals("source.txt", AllocineExtraction.sourceFile);
        assertEquals("result.txt", AllocineExtraction.resultFile);
    }

    @Test
    public void testFilesArgument() {
        String[] testArgs = { "-s", "sourcePath.txt", "-r", "resultPath.txt"};
        AllocineExtraction.parseArgs(testArgs);
        assertEquals("sourcePath.txt", AllocineExtraction.sourceFile);
        assertEquals("resultPath.txt", AllocineExtraction.resultFile);

        String[] newTestArgs = { "--source", "newSourcePath.txt", "--result", "newResultPath.txt"};
        AllocineExtraction.parseArgs(newTestArgs);
        assertEquals("newSourcePath.txt", AllocineExtraction.sourceFile);
        assertEquals("newResultPath.txt", AllocineExtraction.resultFile);
    }
}
