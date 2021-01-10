package ars.yukihiro.repository;

import ars.yukihiro.entity.Node;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Node用リポジトリクラス
 * @atuher yukihiro adachi
 */
@Repository
public class NodeRepository extends AbstractRepository{

    @PersistenceContext
    EntityManager entityManager;

    public Node findNode(String nodeId) {
        return entityManager.find(Node.class, nodeId);
    }

    @Transactional
    public void updateNode(Node entity) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Node> update = cb.createCriteriaUpdate(Node.class);
            Root<Node> root = update.from(Node.class);
            // TODO 文字化け対応
            // TODO メタモデル使いたい
            System.out.println(new SimpleDateFormat("yyyyMMddHHmmSS.sss").format(new Date()));
            update
                    .set(root.get("hierarchy"), entity.getHierarchy())
                    .set(root.get("nodeType"), entity.getNodeType())
                    .set(root.get("nodeNmLgc"), entity.getNodeNmLgc())
                    .set(root.get("nodeNmPsc"), entity.getNodeNmPsc())
                    .set(root.get("contentsId"), entity.getContentsId())
                    .set(root.get("upDt"), getCurrentTimeStamp())
                    // TODO 登録UserNameを設定する
                    .set(root.get("upNm"), "admin")
                    .where(cb.equal(root.get("nodeId"), entity.getNodeId()));

            Query query = entityManager.createQuery(update);
            entityManager.joinTransaction();
            query.executeUpdate();
            entityManager.flush();
        } catch (Exception e) {
           throw e;
        }
    }
}
