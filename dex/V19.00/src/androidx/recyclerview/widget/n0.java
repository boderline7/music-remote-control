package androidx.recyclerview.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class n0 extends l0 {
    private int mDefaultDragDirs;
    private int mDefaultSwipeDirs;

    public n0(int i7, int i8) {
        this.mDefaultSwipeDirs = i8;
        this.mDefaultDragDirs = i7;
    }

    public int getDragDirs(RecyclerView recyclerView, j2 j2Var) {
        return this.mDefaultDragDirs;
    }

    @Override // androidx.recyclerview.widget.l0
    public int getMovementFlags(RecyclerView recyclerView, j2 j2Var) {
        return l0.makeMovementFlags(getDragDirs(recyclerView, j2Var), getSwipeDirs(recyclerView, j2Var));
    }

    public int getSwipeDirs(RecyclerView recyclerView, j2 j2Var) {
        return this.mDefaultSwipeDirs;
    }

    public void setDefaultDragDirs(int i7) {
        this.mDefaultDragDirs = i7;
    }

    public void setDefaultSwipeDirs(int i7) {
        this.mDefaultSwipeDirs = i7;
    }
}
