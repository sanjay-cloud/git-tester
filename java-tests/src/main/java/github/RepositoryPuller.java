package github;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.CanceledException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidConfigurationException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.RefNotAdvertisedException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class RepositoryPuller {
	public static void pullRepository(String username, String password, String repositoryPath)
			throws WrongRepositoryStateException, InvalidConfigurationException, InvalidRemoteException,
			CanceledException, RefNotFoundException, RefNotAdvertisedException, NoHeadException, TransportException,
			GitAPIException, IOException {
		Git git = Git.open(new File(repositoryPath));
		git.pull().setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
				.setRemote("origin").setRemoteBranchName("master").call();
		Repository repository = git.getRepository();
	}

	public static void main(String[] args) {
		try {
			pullRepository("sanjay-cloud", "githubSan8", "/path/to/reposter");
		} catch (WrongRepositoryStateException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
		} catch (CanceledException e) {
			e.printStackTrace();
		} catch (RefNotFoundException e) {
			e.printStackTrace();
		} catch (RefNotAdvertisedException e) {
			e.printStackTrace();
		} catch (NoHeadException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
