# github-pull-request-examiner

## Build and Run

1. Clean old files (if there is any) by running `mvn clean`
2. Replace template information with proper one into this code block https://github.com/tanvn/github-pull-request-examiner/blob/master/src/main/java/tanvn/github/api/app/PRChecker.java#L25-L29
3. Build executable jar by running `mvn package`
4. Run `./pr_checker {pullRequestId}` and check the result written into `result/result.txt`
