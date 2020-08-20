package wami.ikechukwu.kanu.repository

enum class NetworkStatus {
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val networkStatus: NetworkStatus, val msg: String) {
    companion object {
        //            TODO FIX USING THE SUGGESTION FROM ANDROID STUDIO
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState

        init {
            LOADED = NetworkState(NetworkStatus.SUCCESS, "Success")
            LOADING = NetworkState(NetworkStatus.RUNNING, "Running")
            ERROR = NetworkState(NetworkStatus.FAILED, "Something went wrong")

        }
    }
}