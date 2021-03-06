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
package com.airback.common.domain;

import com.airback.db.metadata.Column;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
public class ReportBugIssueWithBLOBs extends ReportBugIssue {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.userAgent
     *
     * @mbggenerated Mon Dec 07 11:25:13 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("userAgent")
    private String useragent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.errorTrace
     *
     * @mbggenerated Mon Dec 07 11:25:13 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("errorTrace")
    private String errortrace;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.userAgent
     *
     * @return the value of s_report_bug_issue.userAgent
     *
     * @mbggenerated Mon Dec 07 11:25:13 ICT 2015
     */
    public String getUseragent() {
        return useragent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_report_bug_issue.userAgent
     *
     * @param useragent the value for s_report_bug_issue.userAgent
     *
     * @mbggenerated Mon Dec 07 11:25:13 ICT 2015
     */
    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.errorTrace
     *
     * @return the value of s_report_bug_issue.errorTrace
     *
     * @mbggenerated Mon Dec 07 11:25:13 ICT 2015
     */
    public String getErrortrace() {
        return errortrace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_report_bug_issue.errorTrace
     *
     * @param errortrace the value for s_report_bug_issue.errorTrace
     *
     * @mbggenerated Mon Dec 07 11:25:13 ICT 2015
     */
    public void setErrortrace(String errortrace) {
        this.errortrace = errortrace;
    }

    public enum Field {
        id,
        saccountid,
        username,
        ipaddress,
        countryCode,
        useragent,
        errortrace;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}