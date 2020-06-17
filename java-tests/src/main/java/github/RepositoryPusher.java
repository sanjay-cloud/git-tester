package github;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class RepositoryPusher {
	public static void commitAndPush(String repositoryPath, String message, String username, String password,
			String uri) throws Exception {
		Git git = Git.open(new File(repositoryPath));
		git.add().addFilepattern(".").call();
		git.commit().setMessage(message).setCommitter("sanjay", "sanjay").call();
		git.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password)).setRemote(uri)
				.add("master").call();
	}

	public static void main(String[] args) {
		try {
			commitAndPush("/git-repository", "remote test", "sanjay-cloud", "githubSan8",
					"https://github.com/sanjay-cloud/git-tester");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
