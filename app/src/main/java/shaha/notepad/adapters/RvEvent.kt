package shaha.notepad.adapters

import androidx.constraintlayout.widget.ConstraintLayout
import shaha.notepad.db.UserEntity

interface RvEvent {
    fun edit(userEntity: UserEntity, view: ConstraintLayout)

    //    fun edit2(history: History, view: View)
    fun menuClick2(userEntity: UserEntity, view: Int)
//    fun setText(dialog: AlertDialog, keyDialog: AddDialogBinding, izoh: Izoh)
//    fun setText2(dialog: AlertDialog, keyDialog:EditBalanceBinding, izoh: Izoh)

}