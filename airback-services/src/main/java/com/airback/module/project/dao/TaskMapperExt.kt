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
package com.airback.module.project.dao

import com.airback.common.domain.GroupItem
import com.airback.db.persistence.ISearchableDAO
import com.airback.module.project.domain.SimpleTask
import com.airback.module.project.domain.criteria.TaskSearchCriteria
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * @author airback Ltd.
 * @since 1.0
 */
@Mapper
interface TaskMapperExt : ISearchableDAO<TaskSearchCriteria> {

    fun findTaskById(taskId: Int): SimpleTask?

    fun getPrioritySummary(@Param("searchCriteria") criteria: TaskSearchCriteria): List<GroupItem>

    fun getStatusSummary(@Param("searchCriteria") criteria: TaskSearchCriteria): List<GroupItem>

    fun getAssignedDefectsSummary(@Param("searchCriteria") criteria: TaskSearchCriteria): List<GroupItem>

}
