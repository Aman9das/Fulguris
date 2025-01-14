/*
 * Copyright (C) 2017-2019 Hazuki
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

package jp.hazuki.yuzubrowser.adblock.filter.unified

class SingleDomainMap(override val include: Boolean, private val domain: String) : DomainMap {
    override val size: Int
        get() = 1

    override fun get(domain: String): Boolean? {
//        return if (this.domain == domain) include else null
        // see https://adblockplus.org/en/filter-cheatsheet: also matches subdomains
        // so either domains equal or domain = *.(this.domain)
        return if (this.domain == domain || domain.endsWith(".${this.domain}")) include else null
    }

    override fun getKey(index: Int): String {
        if (index != 0) throw IndexOutOfBoundsException()
        return domain
    }

    override fun getValue(index: Int): Boolean {
        if (index != 0) throw IndexOutOfBoundsException()
        return include
    }
}
