import com.logger.data.Tag

class LogData(
    val tag: Tag,
    val message: String,
)

data class ApiRequestData(
    val url: String,
    val userId: String,
    val requestMethod: String,
    val responseCode: Int,
    val responseTime: Long,
    val errorBody: String?
) {
    override fun toString(): String {
        return "url:$url, userId:$userId, requestMethod:$requestMethod, responseCode:$responseCode, responseTime:$responseTime, errorBody:$errorBody"
    }
}