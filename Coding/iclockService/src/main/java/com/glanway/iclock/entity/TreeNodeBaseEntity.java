package com.glanway.iclock.entity;

import org.ponly.webbase.entity.PathTreeNode;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 树形结构节点
 *
 * @author yangchanghe
 */
public abstract class TreeNodeBaseEntity<N extends TreeNodeBaseEntity<N>> extends BaseEntity implements PathTreeNode<N, Long> {
    protected N parent;
    protected List<N> children;
    /**
     * 层级/深度
     *
     * @Column DEPTH
     */
    private Integer depth;

    /**
     * 路径
     *
     * @Column PATH
     */
    @Size(max = 255)
    private String path;

    /**
     * 是否叶子节点
     *
     * @Column IS_LEAF
     */
    private Boolean isLeaf = Boolean.FALSE;


    @Override
    public Long getNid() {
        return this.getId();
    }

    @Override
    public void setNid(Long id) {
        this.setId(id);
    }

    /*
    @ManyToOne(optional = true)
    @JoinColumn(name = "PARENT_ID")
    @JsonManagedReference
    */
    @Override
    public N getParent() {
        return parent;
    }

    @Override
    public void setParent(N parent) {
        this.parent = parent;
    }

    /*
    @JsonBackReference
    @OneToMany(mappedBy = "parent")
    */
    @Override
    public List<N> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<N> children) {
        this.children = children;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Integer getDepth() {
        return depth;
    }

    @Override
    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    @Override
    public Boolean getIsLeaf() {
        return isLeaf;
    }

    @Override
    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
}
