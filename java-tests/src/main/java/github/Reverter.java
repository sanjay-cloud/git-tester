package github;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.RevertCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

public class Reverter {
	public static void reverOneCommit(String repositoryPath) throws IOException, GitAPIException {
		Git git = Git.open(new File(repositoryPath));
		List<Ref> branches = git.branchList().setListMode(ListMode.ALL).call();
		RevWalk walk = new RevWalk(git.getRepository());
		RevCommit youngestCommit = null;
		for (Ref branch : branches) {
			RevCommit commit = walk.parseCommit(branch.getObjectId());
			if (youngestCommit == null
					|| commit.getAuthorIdent().getWhen().compareTo(youngestCommit.getAuthorIdent().getWhen()) > 0)
				youngestCommit = commit;
		}
		RevertCommand revert = git.revert();
		revert.include(youngestCommit).call();

	}

	public static void main(String[] args) throws Exception {
		reverOneCommit("/path/to/reposter");
		RepositoryPusher.commitAndPush("/path/to/reposter", "remote test", "sanjay-cloud", "githubSan8",
				"https://github.com/sanjay-cloud/private-test.git");
	}
}
