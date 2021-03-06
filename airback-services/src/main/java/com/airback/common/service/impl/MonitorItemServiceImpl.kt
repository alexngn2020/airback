/**
 * Copyright © airback
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.airback.common.service.impl

import com.airback.common.dao.MonitorItemMapper
import com.airback.common.dao.MonitorItemMapperExt
import com.airback.common.domain.MonitorItem
import com.airback.common.domain.MonitorItemExample
import com.airback.common.domain.criteria.MonitorSearchCriteria
import com.airback.common.service.MonitorItemService
import com.airback.db.persistence.ICrudGenericDAO
import com.airback.db.persistence.ISearchableDAO
import com.airback.db.persistence.service.DefaultService
import com.airback.module.user.domain.SimpleUser
import org.springframework.stereotype.Service

/**
 * @author airback Ltd.
 * @since 1.0
 */
@Service
class MonitorItemServiceImpl(private val monitorItemMapper: MonitorItemMapper,
                             private val monitorItemMapperExt: MonitorItemMapperExt) : DefaultService<Int, MonitorItem, MonitorSearchCriteria>(), MonitorItemService {

    override val crudMapper: ICrudGenericDAO<Int, MonitorItem>
        get() = monitorItemMapper as ICrudGenericDAO<Int, MonitorItem>

    override val searchMapper: ISearchableDAO<MonitorSearchCriteria>
        get() = monitorItemMapperExt

    override fun isUserWatchingItem(username: String, type: String, typeId: String): Boolean {
        val ex = MonitorItemExample()
        ex.createCriteria().andUsernameEqualTo(username).andTypeEqualTo(type).andTypeidEqualTo(typeId)
        return monitorItemMapper.countByExample(ex) > 0
    }

    override fun saveWithSession(record: MonitorItem, username: String?): Int {
        val ex = MonitorItemExample()
        ex.createCriteria().andTypeEqualTo(record.type).andTypeidEqualTo(record.typeid).andUsernameEqualTo(record.username)
        val count = monitorItemMapper.countByExample(ex)
        return if (count > 0) 1 else super.saveWithSession(record, username)
    }

    override fun saveMonitorItems(monitorItems: Collection<MonitorItem>) {
        if (monitorItems.isNotEmpty()) {
            monitorItemMapperExt.saveMonitorItems(monitorItems)
        }
    }

    override fun getWatchers(type: String, typeId: Int): List<SimpleUser> =
            monitorItemMapperExt.getWatchers(type, typeId)

    override fun getNextItemKey(criteria: MonitorSearchCriteria): Int? = null

    override fun getPreviousItemKey(criteria: MonitorSearchCriteria): Int? = null
}
