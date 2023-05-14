import android.util.Log

class WebApiResponse {
    var retCode: String = ""
    var status: String = ""
    var body: String = ""


    companion object {
        fun deserialize(data:Map<Any?, Any?>): WebApiResponse {
            val obj = WebApiResponse()
            obj.retCode = data["retCode"].toString()
            obj.status = data["status"].toString()
            obj.body = data["body"].toString()

            Log.d("KURO", obj.toString())

            return obj
        }
    }

    override fun toString(): String {
        return "WebApiResponse(retCode='$retCode', status='$status', body='$body')"
    }
}
