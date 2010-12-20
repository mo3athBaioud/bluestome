/**
 * �ļ�com.sky.spirit.basic.dto.DownloadFileDTO.java ������2008 2008-9-8 ����10:12:41
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-8 ����10:12:41
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.cache;

import java.io.Serializable;

/**
 * Cache����DTO�����ڴ洢��Cache�е�ʵ��
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-8 ����10:12:41
 * @version 1.0.0<br>
 *          ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 * 
 */
public class CacheEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4605988848680404234L;
	private Object content;// �ļ������ֽ�
	private long right;// ʱ���

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public long getRight() {
		return right;
	}

	public void setRight(long right) {
		this.right = right;
	}

//	public String toString() {
//		return ToStringBuilder.reflectionToString(this,
//				ToStringStyle.MULTI_LINE_STYLE);
//	}
}
