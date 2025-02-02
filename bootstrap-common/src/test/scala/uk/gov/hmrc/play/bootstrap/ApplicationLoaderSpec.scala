/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.play.bootstrap

import play.api.{Configuration, Environment}
import uk.gov.hmrc.play.bootstrap.config.Base64ConfigDecoderTests

class ApplicationLoaderSpec extends Base64ConfigDecoderTests {

  val loader = new ApplicationLoader()

  override def decode(config: (String, Any)*): Configuration = {

    val context = {
      val ctx = play.api.ApplicationLoader.Context.create(Environment.simple())
      ctx.copy(initialConfiguration = ctx.initialConfiguration.withFallback(Configuration(config: _*)))
    }

    loader.load(context).configuration
  }

  ".load" must {
    behave like aBase64Decoder()
  }
}
