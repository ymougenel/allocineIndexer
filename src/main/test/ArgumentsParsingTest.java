import org.junit.Test;
import util.ArgumentsUtil;

import static org.junit.Assert.assertEquals;
import static util.ArgumentsUtil.resultFile;
import static util.ArgumentsUtil.sourceFile;


public class ArgumentsParsingTest {


    @Test
    public void testDefaultValues() {
        assertEquals("source.txt", sourceFile);
        assertEquals("result.txt", resultFile);
    }

    @Test
    public void testFilesArgument() {
        String[] testArgs = { "-s", "sourcePath.txt", "-r", "resultPath.txt"};
        ArgumentsUtil.parseArgs(testArgs);
        assertEquals("sourcePath.txt", sourceFile);
        assertEquals("resultPath.txt", resultFile);

        String[] newTestArgs = { "--source", "newSourcePath.txt", "--result", "newResultPath.txt"};
        ArgumentsUtil.parseArgs(newTestArgs);
        assertEquals("newSourcePath.txt", sourceFile);
        assertEquals("newResultPath.txt", resultFile);
    }
}
