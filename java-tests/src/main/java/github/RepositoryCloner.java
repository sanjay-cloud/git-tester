package github;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class RepositoryCloner {
	public static Git cloneRepository(String uri, String repositoryPath, String username, String password)
			throws InvalidRemoteException, TransportException, GitAPIException {
		Git git = Git.cloneRepository().setURI(uri).setDirectory(new File(repositoryPath))
				.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password)).call();
		return git;
	}

	public static void main(String[] args) {
		try {
			cloneRepository("https://github.com/sanjay-cloud/git-tester", "/git-repository", "sanjay-cloud",
					"githubSan8");
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}
}
