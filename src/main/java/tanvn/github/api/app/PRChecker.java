package tanvn.github.api.app;

import org.eclipse.egit.github.core.CommitFile;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class PRChecker {

    private static final Logger logger = LoggerFactory.getLogger(PRChecker.class);
    private PullRequestService service;
    private RepositoryId repo;
    private final static String WEB_PREFIX = "web/";
    private final  static Predicate<CommitFile> webChangePredicate = p -> p.getFilename().startsWith(WEB_PREFIX);
    private final static Predicate<CommitFile> apiChangePredicate = p -> {
        String fileName = p.getFilename();
        return fileName.startsWith("eng-") || fileName.startsWith("prf-") || fileName.endsWith(".scala");
    };

    public PRChecker() {
        service = new PullRequestService();
        service.getClient().setOAuth2Token("my_real_token"); //TODO replace with system property
        repo = new RepositoryId("owner", "name"); //TODO replace with system property
        }

    public boolean isWebChanged (int prNo) {
        return isPrMatched(prNo, webChangePredicate);
    }

    public boolean isApiChanged (int prNo) {
        return isPrMatched(prNo, apiChangePredicate);
    }

    private boolean isPrMatched(int prNo, Predicate<CommitFile> condition) {
        try {
            List<CommitFile> files = service.getFiles(repo, prNo);
            logger.debug("Pr-{} : total changed files : {}",prNo, files.size());
            files.forEach(f -> logger.debug("{} : {}", f.getFilename(), f.getStatus()));
            return files.stream().anyMatch(condition);
        } catch (IOException e) {
            logger.error("isPrMatched error", e);
            e.printStackTrace();
        }
        return false;
    }
}
