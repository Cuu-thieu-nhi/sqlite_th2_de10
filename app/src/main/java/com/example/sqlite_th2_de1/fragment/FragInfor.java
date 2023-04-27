package com.example.sqlite_th2_de1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sqlite_th2_de1.R;

public class FragInfor extends Fragment {

    private ImageView avatar;
    private TextView hoVaTen;
    private TextView soThich;
    private TextView gioiThieu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_infor, container, false);

        avatar = view.findViewById(R.id.avatar);
        hoVaTen = view.findViewById(R.id.ho_va_ten);
        soThich = view.findViewById(R.id.so_thich);
        gioiThieu = view.findViewById(R.id.gioi_thieu_ban_than);

        hoVaTen.setText("Đỗ Danh Tuấn");
        soThich.setText("Cựu thiếu nhi");
        gioiThieu.setText("Khi hát lên tiếng ca gởi về người yêu phương xa\n" +
                "Ta át tiếng gió mưa thét gào cuộn dâng phong ba\n" +
                "Em ơi nghe chăng lời trái tim vọng ra\n" +
                "Rung trong không gian mặt biển sôi ầm vang\n" +
                "Qua núi biếc trập trùng xa xa\n" +
                "Qua áng mây che mờ quê ta\n" +
                "Tiếng ca đời đời chung thuỷ thiết tha\n" +
                "Em có nghe tiếng ca chứa đựng hận thù sâu xa\n" +
                "Đã biến tình đôi ta\n" +
                "Thành những cánh sao toả sáng\n" +
                "Vượt băng băng qua đêm tối\n" +
                "Tìm hương hoa\n" +
                "Bến nước Cửu Long còn đó em ơi\n" +
                "Bãi lúa nương dâu còn mãi muôn đời\n" +
                "Và còn duyên tình ta thắm trong tiếng ca\n" +
                "Không thể xoá nhoà\n" +
                "Khi đã nghe tiếng ca của lòng người yêu quê ta\n" +
                "Em hãy ngước mắt lên ngắm nhìn trời xanh quê ta\n" +
                "Chim giăng giăng bay ngoài nắng xuân đẹp thay\n" +
                "Tan cơn phong ba lòng đất yên rồi đây\n" +
                "Em hãy nở nụ cười tươi xinh\n" +
                "Như đoá hoa xuân chào riêng anh\n" +
                "Nói nhau ngàn lời qua đôi mắt xanh\n" +
                "Ta hát chung tiếng ca vang động từ ngàn phương xa\n" +
                "Xua kẻ thù đi mau\n" +
                "Dập tắt chiến tranh đẫm máu\n" +
                "Đập tan ngay bao đau khổ và chia ly\n" +
                "Giữ lấy đức tin bền vững em ơi\n" +
                "Giữ lấy trái tim đòi sống muôn đời\n" +
                "Làm một bài tình ca của đôi lứa ta\n" +
                "Dâng cả bao người");
        return view;
    }
}
