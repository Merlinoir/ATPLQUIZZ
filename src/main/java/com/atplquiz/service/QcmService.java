package com.atplquiz.service;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.atplquiz.entity.Qcm;
import com.atplquiz.entity.Question;
import com.atplquiz.entity.QuestionReponse;
import com.atplquiz.entity.Reponse;

@Service
public class QcmService {

	@Autowired
	QuestionService qs;
	
	@Autowired
	ReponseService rs;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	private final static int NB_QUESTIONS = 19;

	private static Log log = LogFactory.getLog(QcmService.class);

	public List<Qcm> findAll() {
		List<Qcm> qcms = this.jdbcTemplate.query("select * from QCM",
				new RowMapper<Qcm>() {
					public Qcm mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Qcm qcm = new Qcm();
						qcm.setId(rs.getString("id_qcm"));
						qcm.setIdUser(rs.getLong("id_user"));
						qcm.setDate(rs.getDate("date"));
						return qcm;
					}
				});
		// ECRITURE DANS LOG
		for (Qcm qcm : qcms) {
			log.info("findAll rest request result : " + qcm.toString());
		}
		return qcms;
	}

	public List<Qcm> findById(@PathVariable String id) {
		// REQUETE
		List<Qcm> qcms = this.jdbcTemplate.query(
				"select * from QCM where ID_QCM =" + id, new RowMapper<Qcm>() {
					public Qcm mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Qcm qcm = new Qcm();
						qcm.setId(rs.getString("id_qcm"));
						qcm.setIdUser(rs.getLong("id_user"));
						qcm.setDate(rs.getDate("date"));
						return qcm;
					}
				});
		// ECRITURE DANS LOG
		for (Qcm qcm : qcms) {
			log.info("findById rest request result : " + qcm.toString());
		}
		return qcms;
	}

	public Qcm createQcm(Qcm qcm) {
		log.info("Creating qcm :" + qcm.toString());
		final String id = qcm.getId();
		final long idUser = qcm.getIdUser();
		final Date date = qcm.getDate();
		this.jdbcTemplate.update(
				"insert into QCM (ID_QCM, ID_USER, DATE) values (?,?,?)", id,
				idUser, date);
		return qcm;
	}

	public Qcm updateQcm(Qcm qcm) {
		final String id = qcm.getId();
		final long idUser = qcm.getIdUser();
		final Date date = qcm.getDate();
		this.jdbcTemplate.update("update QCM set ID_USER=?, DATE=? "
				+ " where ID_QCM=? ", idUser, date, id);
		log.info("Qcm after update : " + qcm.toString());
		return qcm;

	}

	public void deleteQcm(long id) {
		log.info("Deleting Qcm record ID " + id);
		jdbcTemplate.update("DELETE FROM QCM WHERE ID_QCM = ?;",
				new Object[] { id });
	}

	public List<QuestionReponse> generateQuestionReponseByTheme(long idTheme) {
		List<QuestionReponse> questionReponseList = new ArrayList<QuestionReponse>();
		List<Question> randomQuestions = new ArrayList<Question>();
		List<Reponse> answersToRandomQuestion = new ArrayList<Reponse>();
		// Recuperer les question par rapport au theme
		List<Question> questions = qs.findByThemeId(idTheme);

		// Extraire 10 Questions e math.random

		while (randomQuestions.size() != NB_QUESTIONS) {
			int index = randomWithRange(0, questions.size() - 1);
			if (!randomQuestions.contains(questions.get(index))) {
				randomQuestions.add(questions.get(index));
			}
		}
		if (randomQuestions != null && randomQuestions.size() == NB_QUESTIONS) {
			for (Question question : randomQuestions) {
				answersToRandomQuestion = rs.findReponseByIdQuestion(question
						.getId());
				questionReponseList.add(new QuestionReponse(question,
						answersToRandomQuestion));
			}
		}
		return questionReponseList;
	}

	private int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
}