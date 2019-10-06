package tanvn.github.api.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;

public class App 
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        // Check how many arguments were passed in
        if(args.length == 0)
        {
            System.out.println("Proper Usage is: java program pullRequestId");
            System.exit(0);
        }
        String prId = args[0];

        Integer prNo = parsePR(prId);
        if(prNo != null) {
            PRChecker checker = new PRChecker();
            boolean isWebChanged = checker.isWebChanged(prNo);
            boolean isApiChanged = checker.isApiChanged(prNo);
            logger.info("isWebChanged= {}, isApiChanged = {}", isWebChanged,isApiChanged);
            List<String> lines = Arrays.asList(String.format("pr=%s", prNo),String.format("shouldBuildWeb=%s", isWebChanged), String.format("shouldBuildApi=%s", isApiChanged));
            ResultWriter.write(lines);

        }
    }

    private static Integer parsePR( String prId ) {
        try {
            return Integer.parseInt(prId);
        } catch (NumberFormatException e) {
            logger.error("isPrMatched error", e);
        }
        return null;
    }
}
