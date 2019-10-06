# github-pull-request-examiner

## Build and Run

1. Build exectable jar by running `mvn package`
2. Replace template information with proper one into this code block https://github.com/tanvn/github-pull-request-examiner/blob/master/src/main/java/tanvn/github/api/app/PRChecker.java#L25-L29
3. Run `./pr_checker {pullRequestId}` and check the result written into `result/result.txt`
