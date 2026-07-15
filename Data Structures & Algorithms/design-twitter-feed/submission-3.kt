data class User(
    val id: Int,
    val tweets: MutableList<Tweet> = mutableListOf(),
    var timeline: MutableList<Tweet> = mutableListOf(),
    val following: MutableSet<Int> = mutableSetOf(),
    val followedBy: MutableSet<Int> = mutableSetOf()
) 

data class Tweet(
    val id: Int, 
    val authorId: Int, 
    val timestamp: Int
)

class Twitter {
    val allUsers: MutableMap<Int, User> = mutableMapOf()

    private var tweetTimestamp: Int = 0

    fun getTimestamp(): Int {
        tweetTimestamp += 1
        return tweetTimestamp
    }


    fun postTweet(userId: Int, tweetId: Int) {
        if (!allUsers.contains(userId)) {
            allUsers[userId] = User(userId)
        }
        val user = allUsers.getValue(userId)
        val newTweet = Tweet(tweetId, userId, getTimestamp())
        user.tweets.add(newTweet)
        user.timeline.add(newTweet)
        for (followerId in user.followedBy) {
            val follower = allUsers.getValue(followerId)
            follower.timeline.add(newTweet)
        }
    }

    fun getNewsFeed(userId: Int): List<Int> {
        val result = mutableListOf<Int>()
        val user = allUsers.getValue(userId)
        var i = user.timeline.size-1
        while (i >= 0 && result.size < 10) {
            result.add(user.timeline[i].id)
            i--
        }
        return result
    }

    fun follow(followerId: Int, followeeId: Int) {
        if (followerId == followeeId) {
            return
        } 
        if (!allUsers.contains(followerId)) {
            allUsers[followerId] = User(followerId)
        }
        if (!allUsers.contains(followeeId)) {
            allUsers[followeeId] = User(followeeId)
        }

        val follower = allUsers.getValue(followerId)
        val followee = allUsers.getValue(followeeId)
        if (followee.followedBy.contains(followeeId)||follower.following.contains(followeeId)) {
            return
        }

        followee.followedBy.add(followerId)
        follower.following.add(followeeId)

        if (follower.timeline.isEmpty()) {
            follower.timeline = followee.tweets.toMutableList()
            return
        }
        
        val followerTimelineNew = (follower.timeline + followee.tweets).sortedBy {it.timestamp}
        follower.timeline = followerTimelineNew.toMutableList()
    }

    fun unfollow(followerId: Int, followeeId: Int) {
        val follower = allUsers.getValue(followerId)
        val followee = allUsers.getValue(followeeId)
        if (!followee.followedBy.contains(followerId)) {
            return
        }
        followee.followedBy.remove(followerId)
        follower.following.remove(followeeId)
        val followerTimelineNew = follower.timeline.filter {it.authorId != followee.id}.toMutableList()
        follower.timeline = followerTimelineNew
    }
}
