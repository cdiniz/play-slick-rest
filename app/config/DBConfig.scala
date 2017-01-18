package config

import slick.driver.JdbcProfile

object DBConfig {
  var db : JdbcProfile#Backend#Database = null
  var driver : JdbcProfile = null
}
