//package services
//
//import model.Geo
//import model.User
//import model.UserAddress
//import model.UserCompany
//import kotlin.coroutines.CoroutineContext
//
//class UserService(private val coroutineContext: CoroutineContext) {
//
//    suspend fun getUser(id: Int): User {
//        return User(
//            1,
//            "name",
//            "uname",
//            "mail@",
//            UserAddress("st", "sui", "Mosc", "123", Geo("1", "2")),
//            phone = "1234567",
//            website = "site.org",
//            company = UserCompany("Com", "ca", "bs")
//        )
//    }
//
//}