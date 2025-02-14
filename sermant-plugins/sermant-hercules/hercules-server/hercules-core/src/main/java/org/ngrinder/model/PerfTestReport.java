/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ngrinder.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.Expose;
import com.huawei.argus.serializer.PerfSceneType;
import com.huawei.argus.serializer.TimestampDatetimeSerializer;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.ngrinder.common.util.DateUtils;
import org.ngrinder.common.util.PathUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.ngrinder.common.util.AccessUtils.getSafe;

/**
 * Performance Test Entity.
 */

@SuppressWarnings({"JpaDataSourceORMInspection", "UnusedDeclaration", "JpaAttributeTypeInspection"})
@Entity
@Table(name = "PERF_TEST_REPORT")
public class PerfTestReport extends MyBaseModel<PerfTestReport> {

	private static final int MARGIN_FOR_ABBREVIATION = 8;

	private static final int MAX_LONG_STRING_SIZE = 9990;

	private static final long serialVersionUID = 1369809450686098944L;

	private static final int MAX_STRING_SIZE = 2048;

	public PerfTestReport() {

	}

	/**
	 * Constructor.
	 *
	 * @param createdUser crested user.
	 */
	public PerfTestReport(User createdUser) {
		this.setCreatedUser(createdUser);
	}

	@Expose
	@Cloneable
	@Column(name = "name")
	private String testName;

	@Expose
	@Cloneable
	@Column(name = "tag_string")
	private String tagString;

	@Expose
	@Cloneable
	@Column(length = MAX_LONG_STRING_SIZE)
	private String description;

	@Expose
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Expose
	@Cloneable
	/** ignoreSampleCount value, default to 0. */
	@Column(name = "ignore_sample_count")
	private Integer ignoreSampleCount;

	@Expose
	/** the scheduled time of this test. */
	@Column(name = "scheduled_time")
	@Index(name = "scheduled_time_index")
	@JsonSerialize(using = TimestampDatetimeSerializer.class)
	private Date scheduledTime;

	@Expose
	/** the start time of this test. */
	@Column(name = "start_time")
	@JsonSerialize(using = TimestampDatetimeSerializer.class)
	private Date startTime;

	@Expose
	/** the finish time of this test. */
	@Column(name = "finish_time")
	@JsonSerialize(using = TimestampDatetimeSerializer.class)
	private Date finishTime;

	/**
	 * the target host to test.
	 */
	@Expose
	@Cloneable
//	@Column(name = "target_hosts")
	@Column(name = "target_hosts", length = 65535)
	private String targetHosts;


	/**
	 * Use rampUp or not.
	 */
	@Expose
	@Cloneable
	@Column(name = "use_rampup", columnDefinition = "char(1)")
	@Type(type = "true_false")
	private Boolean useRampUp;

	public RampUp getRampUpType() {
		return rampUpType;
	}

	public void setRampUpType(RampUp rampUpType) {
		this.rampUpType = rampUpType;
	}

	/**
	 * Use rampUp or not.
	 */
	@Expose
	@Cloneable
	@Column(name = "ramp_up_type")
	@Enumerated(EnumType.STRING)
	private RampUp rampUpType;


	/**
	 * The threshold code, R for run count; D for duration.
	 */
	@Expose
	@Cloneable
	@Column(name = "threshold")
	private String threshold;

	@Expose
	@Cloneable
	@Column(name = "script_name")
	private String scriptName;

	@Expose
	@Cloneable
	@Column(name = "duration")
	private Long duration;

	@Expose
	@Cloneable
	@Column(name = "run_count")
	private Integer runCount;

	@Expose
	@Cloneable
	@Column(name = "agent_count")
	private Integer agentCount;

	@Expose
	@Cloneable
	@Column(name = "vuser_per_agent")
	private Integer vuserPerAgent;

	@Expose
	@Cloneable
	@Column(name = "processes")
	private Integer processes;

	@Expose
	@Cloneable
	@Column(name = "ramp_up_init_count")
	private Integer rampUpInitCount;

	@Expose
	@Cloneable
	@Column(name = "ramp_up_init_sleep_time")
	private Integer rampUpInitSleepTime;

	@Expose
	@Cloneable
	@Column(name = "ramp_up_step")
	private Integer rampUpStep;

	@Expose
	@Cloneable
	@Column(name = "ramp_up_increment_interval")
	private Integer rampUpIncrementInterval;

	@Expose
	@Cloneable
	@Column(name = "threads")
	private Integer threads;

	// followings are test result members
	@Expose
	@Column(name = "tests")
	private Long tests;

	@Expose
	@Column(name = "errors")
	private Long errors;

	@Expose
	@Column(name = "mean_test_time")
	private Double meanTestTime;

	@Expose
	@Column(name = "test_time_standard_deviation")
	private Double testTimeStandardDeviation;

	@Expose
	@Column(name = "tps")
	private Double tps;

	@Expose
	@Column(name = "peak_tps")
	private Double peakTps;

	@Expose
	@Column(name = "test_error_cause")
	@Enumerated(EnumType.STRING)
	private Status testErrorCause;

	@Expose
	@Column(name = "progress_message", length = MAX_STRING_SIZE)
	private String progressMessage;

	@Expose
	@Column(name = "test_comment", length = MAX_STRING_SIZE)
	private String testComment;

	@Expose
	@Column(name = "script_revision")
	private Long scriptRevision;

	@Expose
	@Column(name = "stop_request")
	@Type(type = "true_false")
	private Boolean stopRequest;

	@Expose
	@Cloneable
	@Column(name = "region")
	private String region;

	@Expose
	@Cloneable
	@Column(name = "sampling_interval")
	private Integer samplingInterval;

	@Expose
	@Cloneable
	@Column(name = "param")
	private String param;

	@Expose
	@Column(name = "perf_test_id")
	private Long perfTestId;


	@Expose
	@Column(name = "run_time")
	private String runTime;

	@Expose
	@Cloneable
	@Column(name = "scene_type")
	@Enumerated(EnumType.ORDINAL)
	private PerfSceneType type;

	@Expose
	@Cloneable
	@Column(name = "agent_ids", length = MAX_STRING_SIZE)
	private String agentIds;

	@PrePersist
	@PreUpdate
	public void init() {
		this.status = getSafe(this.status, Status.SAVED);
		this.agentCount = getSafe(this.agentCount);
		this.processes = getSafe(this.processes, 1);
		this.threads = getSafe(this.threads, 1);
		this.scriptName = getSafe(this.scriptName, "");
		this.testName = getSafe(this.testName, "");
		this.progressMessage = getSafe(this.progressMessage, "");
		this.testComment = getSafe(this.testComment, "");
		this.threshold = getSafe(this.threshold, "D");
		if (isThresholdRunCount()) {
			this.setIgnoreSampleCount(0);
		} else {
			this.ignoreSampleCount = getSafe(this.ignoreSampleCount);
		}
		this.runCount = getSafe(this.runCount);
		this.duration = getSafe(this.duration, 60000L);
		this.samplingInterval = getSafe(this.samplingInterval, 2);
		this.scriptRevision = getSafe(this.scriptRevision, -1L);
		this.param = getSafe(this.param, "");
		this.region = getSafe(this.region, "NONE");
		this.targetHosts = getSafe(this.targetHosts, "");
		this.description = getSafe(this.description, "");
		this.tagString = getSafe(this.tagString, "");
		this.vuserPerAgent = getSafe(this.vuserPerAgent, 1);
		this.useRampUp = getSafe(this.useRampUp, false);
		this.rampUpInitCount = getSafe(this.rampUpInitCount, 0);
		this.rampUpStep = getSafe(this.rampUpStep, 1);
		this.rampUpInitSleepTime = getSafe(this.rampUpInitSleepTime, 0);
		this.rampUpIncrementInterval = getSafe(this.rampUpIncrementInterval, 1000);
		this.rampUpType = getSafe(this.rampUpType, RampUp.PROCESS);
	}


//	public String getTestIdentifier() {
//		return "perftest_" + getId() + "_" + getLastModifiedUser().getUserId();
//	}

	/**
	 * Get total required run count. This is calculated by multiplying agent count, threads,
	 * processes, run count.
	 *
	 * @return run count
	 */
//	public long getTotalRunCount() {
//		return getAgentCount() * getThreads() * getProcesses() * (long) getRunCount();
//	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getRunCount() {
		return runCount;
	}

	public void setRunCount(Integer runCount) {
		this.runCount = runCount;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public Integer getIgnoreSampleCount() {
		return ignoreSampleCount;
	}

	public void setIgnoreSampleCount(Integer ignoreSampleCount) {
		this.ignoreSampleCount = ignoreSampleCount;
	}

	public String getScriptNameInShort() {
		return PathUtils.getShortPath(scriptName);
	}

	public String getDescription() {
		return StringUtils.abbreviate(description, MAX_LONG_STRING_SIZE - MARGIN_FOR_ABBREVIATION);
	}

	public String getLastModifiedDateToStr() {
		return DateUtils.dateToString(getLastModifiedDate());
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTargetHosts() {
		return targetHosts;
	}

	public Long getPerfTestId() { return perfTestId; }

	public void setPerfTestId(Long perfTestId) { this.perfTestId = perfTestId; }

	public String getRunTime() { return runTime; }

	public void setRunTime(String runTime) { this.runTime = runTime; }

	/**
	 * Get ip address of target hosts. if target hosts 'a.com:1.1.1.1' add ip: '1.1.1.1' if target
	 * hosts ':1.1.1.1' add ip: '1.1.1.1' if target hosts '1.1.1.1' add ip: '1.1.1.1'
	 * if www.test.com:0:0:0:0:0:ffff:3d87:a969 add ip: '0:0:0:0:0:ffff:3d87:a969'
	 *
	 * @return host ip list
	 */
	public List<String> getTargetHostIP() {
		List<String> targetIPList = new ArrayList<String>();
		String[] hostsList = StringUtils.split(StringUtils.trimToEmpty(targetHosts), ",");
		for (String hosts : hostsList) {
			String[] addresses = StringUtils.split(hosts, ":");
			if (addresses.length <= 2) {
				targetIPList.add(addresses[addresses.length - 1]);
			} else {
				targetIPList.add(hosts.substring(hosts.indexOf(":") + 1, hosts.length()));
			}
		}
		return targetIPList;
	}

	public void setTargetHosts(String theTarget) {
		this.targetHosts = theTarget;
	}

	public String getThreshold() {
		return threshold;
	}

	public Boolean isThresholdDuration() {
		return "D".equals(getThreshold());
	}

	public Boolean isThresholdRunCount() {
		return "R".equals(getThreshold());
	}


	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getAgentCount() {
		return agentCount;
	}


	public void setAgentCount(Integer agentCount) {
		this.agentCount = agentCount;
	}

	public Integer getVuserPerAgent() {
		return vuserPerAgent;
	}


	public void setVuserPerAgent(Integer vuserPerAgent) {
		this.vuserPerAgent = vuserPerAgent;
	}

	public Integer getProcesses() {
		return processes;
	}


	public void setProcesses(Integer processes) {
		this.processes = processes;
	}

	public Integer getRampUpInitCount() {
		return rampUpInitCount;
	}

	public void setRampUpInitCount(Integer initProcesses) {
		this.rampUpInitCount = initProcesses;
	}

	public Integer getRampUpInitSleepTime() {
		return rampUpInitSleepTime;
	}


	public void setRampUpInitSleepTime(Integer initSleepTime) {
		this.rampUpInitSleepTime = initSleepTime;
	}

	public Integer getRampUpStep() {
		return rampUpStep;
	}


	public void setRampUpStep(Integer processIncrement) {
		this.rampUpStep = processIncrement;
	}

	public Integer getRampUpIncrementInterval() {
		return rampUpIncrementInterval;
	}


	public void setRampUpIncrementInterval(Integer processIncrementInterval) {
		this.rampUpIncrementInterval = processIncrementInterval;
	}

	public Integer getThreads() {
		return threads;
	}


	public void setThreads(Integer threads) {
		this.threads = threads;
	}

	public Long getTests() {
		return tests;
	}

	public void setTests(Long tests) {
		this.tests = tests;
	}

	public Long getErrors() {
		return errors;
	}

	public void setErrors(Long errors) {
		this.errors = errors;
	}

	public Double getMeanTestTime() {
		return meanTestTime;
	}

	public void setMeanTestTime(Double meanTestTime) {
		this.meanTestTime = meanTestTime;
	}

	public Double getTestTimeStandardDeviation() {
		return testTimeStandardDeviation;
	}

	public void setTestTimeStandardDeviation(Double testTimeStandardDeviation) {
		this.testTimeStandardDeviation = testTimeStandardDeviation;
	}

	public Double getTps() {
		return tps;
	}

	public void setTps(Double tps) {
		this.tps = tps;
	}

	public Double getPeakTps() {
		return peakTps;
	}

	public void setPeakTps(Double peakTps) {
		this.peakTps = peakTps;
	}

	public Status getTestErrorCause() {
		return testErrorCause;
	}

	public void setTestErrorCause(Status errorCause) {
		this.testErrorCause = errorCause;
	}

	/**
	 * Get Duration time in HH:MM:SS style.
	 *
	 * @return formatted duration string
	 */
	public String getDurationStr() {
		return DateUtils.ms2Time(this.duration);
	}


	/**
	 * Get Running time in HH:MM:SS style.
	 *
	 * @return formatted runtime string
	 */
	public String getRuntimeStr() {
		long ms = (this.finishTime == null || this.startTime == null) ? 0 : this.finishTime.getTime()
				- this.startTime.getTime();
		return DateUtils.ms2Time(ms);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, "tags");
	}

	public String getProgressMessage() {
		return progressMessage;
	}

	public void setProgressMessage(String progressMessage) {
		this.progressMessage = StringUtils.defaultIfEmpty(StringUtils.right(progressMessage, MAX_STRING_SIZE), "");
	}

	public Boolean getStopRequest() {
		return stopRequest;
	}

	public void setStopRequest(Boolean stopRequest) {
		this.stopRequest = stopRequest;
	}

	public String getTestComment() {
		return testComment;
	}

	public void setTestComment(String testComment) {
		this.testComment = StringUtils.trimToEmpty(StringUtils.right(testComment, MAX_STRING_SIZE));
	}

	public Long getScriptRevision() {
		return scriptRevision;
	}


	public void setScriptRevision(Long scriptRevision) {
		this.scriptRevision = scriptRevision;
	}

	/**
	 * Clear all messages.
	 */
	public void clearMessages() {
		setProgressMessage("");
	}

	public Boolean getUseRampUp() {
		return useRampUp;
	}


	public void setUseRampUp(Boolean useRampUp) {
		this.useRampUp = useRampUp;
	}


	public String getTagString() {
		return tagString;
	}


	public void setTagString(String tagString) {
		this.tagString = tagString;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getSamplingInterval() {
		return samplingInterval;
	}


	public void setSamplingInterval(Integer samplingInterval) {
		this.samplingInterval = samplingInterval;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public void prepare(boolean isClone) {
		if (isClone) {
			this.setId(null);
			this.setTestComment("");
		}
		this.useRampUp = getSafe(this.useRampUp);
	}

	public PerfSceneType getType() {
		if (type == null)
			return PerfSceneType.UNKNOWN;
		return type;
	}

	public void setType(PerfSceneType type) {
		this.type = type;
	}


	public String getAgentIds() {
		return agentIds;
	}

	public void setAgentIds(String agentIds) {
		this.agentIds = agentIds;
	}

}
