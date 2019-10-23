package com.example.githubrowse.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.githubrowse.persistance.ContributionsConverter
import com.example.githubrowse.persistance.OwnerConverter
import com.google.gson.annotations.SerializedName


@Entity(tableName = "repo")
@TypeConverters(OwnerConverter::class,ContributionsConverter::class)
data class Repo (

		@PrimaryKey
		 var id : Int? = -1,
		 var node_id : String? ="",
		 var name : String? ="",
		 var full_name : String? ="",
    	 var owner : Owner? = null,
		@SerializedName("private") var private : Boolean? = false,
		@SerializedName("html_url") var html_url : String? ="",
		@SerializedName("description") var description : String? ="",
		@SerializedName("fork") var fork : Boolean? = false,
		@SerializedName("url") var url : String? = "",
		@SerializedName("archive_url") var archive_url : String? ="",
		@SerializedName("assignees_url") var assignees_url : String? ="",
		@SerializedName("blobs_url") var blobs_url : String? ="",
		@SerializedName("branches_url") var branches_url : String? ="",
		@SerializedName("collaborators_url") var collaborators_url : String? ="",
		@SerializedName("comments_url") var comments_url : String? ="",
		@SerializedName("commits_url") var commits_url : String? ="",
		@SerializedName("compare_url") var compare_url : String? ="",
		@SerializedName("contents_url") var contents_url : String? ="",
		@SerializedName("contributors_url") var contributors_url : String? =" ",
		@SerializedName("deployments_url") var deployments_url : String? ="",
		@SerializedName("downloads_url") var downloads_url : String? ="",
		@SerializedName("events_url") var events_url : String? ="",
		@SerializedName("forks_url") var forks_url : String? ="",
		@SerializedName("git_commits_url") var git_commits_url : String? ="",
		@SerializedName("git_refs_url") var git_refs_url : String? ="",
		@SerializedName("git_tags_url") var git_tags_url : String? ="",
		@SerializedName("git_url") var git_url : String? ="",
		@SerializedName("issue_comment_url") var issue_comment_url : String? ="",
		@SerializedName("issue_events_url") var issue_events_url : String? ="",
		@SerializedName("issues_url") var issues_url : String? ="",
		@SerializedName("keys_url") var keys_urls_url : String? ="",
		@SerializedName("labels_url") var labels_url : String? ="",
		@SerializedName("languages_url") var languages_url : String? ="",
		@SerializedName("merges_url") var merges_url : String? ="",
		@SerializedName("milestones_url") var milestones_url : String? ="",
		@SerializedName("notifications_url") var notifications_url : String? ="",
		@SerializedName("pulls_url") var pulls_url : String? ="",
		@SerializedName("releases_url") var releases_url : String? ="",
		@SerializedName("ssh_url") var ssh_url : String? ="",
		@SerializedName("stargazers_url") var stargazers_url : String? ="",
		@SerializedName("statuses_url") var statuses_url : String? ="",
		@SerializedName("subscribers_url") var subscribers_url : String? ="",
		@SerializedName("subscription_url") var subscription_url : String? ="",
		@SerializedName("tags_url") var tags_url : String?="",
		@SerializedName("teams_url") var teams_url : String? ="",
		@SerializedName("trees_url") var trees_url : String? ="",
		@SerializedName("contributors_data") var contributors : List<Owner>? =null


)
{


}